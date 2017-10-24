import { TestBed, inject } from '@angular/core/testing';

import { PassportDataService } from './passport-data.service';

describe('PassportDataService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PassportDataService]
    });
  });

  it('should be created', inject([PassportDataService], (service: PassportDataService) => {
    expect(service).toBeTruthy();
  }));
});
