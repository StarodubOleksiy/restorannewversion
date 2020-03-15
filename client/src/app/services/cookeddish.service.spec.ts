import { TestBed, inject } from '@angular/core/testing';

import { CookeddishService } from './cookeddish.service';

describe('CookeddishService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CookeddishService]
    });
  });

  it('should be created', inject([CookeddishService], (service: CookeddishService) => {
    expect(service).toBeTruthy();
  }));
});
