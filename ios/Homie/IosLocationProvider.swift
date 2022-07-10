//
//  LocationProvider.swift
//  Homie
//
//  Created by Eirik Vale Aase on 10/07/2022.
//  Copyright © 2022 Homies. All rights reserved.
//

import CoreLocation
import Foundation
import Shared

class IosLocationProvider: NSObject, CLLocationManagerDelegate {
    private let locationManager = CLLocationManager()
    private var authorizationStatusContinuations: [CheckedContinuation<Bool, Never>] = []
    private var requestSingleLocationContinuations: [CheckedContinuation<CLLocation, Error>] = []

    override init() {
        super.init()
        locationManager.delegate = self
    }

    func requestAuthorization() async -> Bool {
        if [.authorizedAlways, .authorizedWhenInUse].contains(locationManager.authorizationStatus) {
            return true
        }

        locationManager.requestWhenInUseAuthorization()

        return await withCheckedContinuation {
            authorizationStatusContinuations.append($0)
        }
    }

    func requestSingleLocation() async throws -> CLLocation {
        locationManager.requestLocation()

        return try await withCheckedThrowingContinuation {
            requestSingleLocationContinuations.append($0)
        }
    }

    func locationManagerDidChangeAuthorization(_ manager: CLLocationManager) {
        switch manager.authorizationStatus {
        case .authorizedAlways,
             .authorizedWhenInUse:
            authorizationStatusContinuations.forEach { $0.resume(returning: true) }
        case .denied,
             .notDetermined,
             .restricted:
            authorizationStatusContinuations.forEach { $0.resume(returning: false) }
        @unknown default:
            authorizationStatusContinuations.forEach { $0.resume(returning: false) }
        }

        authorizationStatusContinuations.removeAll()
    }

    func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {
        requestSingleLocationContinuations.forEach {
            $0.resume(returning: locations[0])
        }
        requestSingleLocationContinuations.removeAll()
    }

    func locationManager(_ manager: CLLocationManager, didFailWithError error: Error) {
        requestSingleLocationContinuations.forEach {
            $0.resume(throwing: error)
        }
        requestSingleLocationContinuations.removeAll()
    }
}
