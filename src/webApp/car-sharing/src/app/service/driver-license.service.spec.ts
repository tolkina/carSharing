import { TestBed, inject } from '@angular/core/testing';

import { DriverLicenseService } from './driver-license.service';

describe('DriverLicenseService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DriverLicenseService]
    });
  });

  it('should be created', inject([DriverLicenseService], (service: DriverLicenseService) => {
    expect(service).toBeTruthy();
  }));
});
