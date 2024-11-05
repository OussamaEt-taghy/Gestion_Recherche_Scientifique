import { TestBed } from '@angular/core/testing';

import { AuthorServicesService } from './author-services.service';

describe('AuthorServicesService', () => {
  let service: AuthorServicesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthorServicesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
