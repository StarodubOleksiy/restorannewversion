import { TestBed, inject } from '@angular/core/testing';

import { HerongService } from './herong.service';

describe('HerongService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [HerongService]
    });
  });

  it('should be created', inject([HerongService], (service: HerongService) => {
    expect(service).toBeTruthy();
  }));
});
