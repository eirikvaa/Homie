import Combine
import CoreLocation
import Shared
import SwiftUI

extension CLLocationCoordinate2D {
    var asCoordinate: Coordinate {
        Coordinate(longitude: Float(self.longitude), latitude: Float(self.latitude))
    }
}

@MainActor
class ContentViewModel: ObservableObject {
    @Published var trip: Trip?
    @Published var locationsResult: [Location] = []
    @Published var currentCoordinate: Coordinate?

    private let tripRepository = TripRepositoryImpl()
    private let locationProvider = IosLocationProvider()
    private var locationTask: Task<Void, Error>?

    /// Currently this will just find a dummy trip from your current position to Oslo Airport.
    /// I'm using Oslo Airport because that was the place ID I had at hand at 3 AM.
    func fetchTrip() {
        self.locationTask = Task {
            do {
                guard await locationProvider.requestAuthorization() else {
                    print("Not authorized to access location services.")
                    return
                }

                let location = try await locationProvider.requestSingleLocation()
                currentCoordinate = location.coordinate.asCoordinate

                let osloAirportPlace = "NSR:StopPlace:58211"
                let trip = try await tripRepository.getTrip(
                    from: Location(name: nil, place: nil, coordinate: currentCoordinate),
                    to: Location(name: nil, place: osloAirportPlace, coordinate: nil)
                )
                self.trip = trip
            } catch {
                print(error.localizedDescription)
            }
        }
    }

    func searchForLocations(text: String) {
        Task {
            do {
                let repo = AutocompleteRepositoryImpl()
                self.locationsResult = try await repo.autocomplete(text: text)
            } catch {
                print(error.localizedDescription)
            }
        }
    }
}

struct ContentView: View {
    @StateObject var viewModel = ContentViewModel()

    var body: some View {
        VStack {
            if let coordinate = viewModel.currentCoordinate {
                Text("Current coordinate: \(coordinate)")
            }
            if let trip = viewModel.trip {
                Text(trip.fromPlace.name ?? "No from place name")
                Text(trip.toPlace.name ?? "No to place name")
                ForEach(trip.tripPatterns, id: \.self) { pattern in
                    Text(pattern.expectedStartTime ?? "No time")
                }
            }
            Button(action: {
                viewModel.fetchTrip()
            }, label: {
                Text("Fetch trip from current position to Oslo Airport")
            })
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
