import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdddishComponent } from './adddish.component';

describe('AdddishComponent', () => {
  let component: AdddishComponent;
  let fixture: ComponentFixture<AdddishComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdddishComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdddishComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
