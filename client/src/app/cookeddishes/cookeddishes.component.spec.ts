import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CookeddishesComponent } from './cookeddishes.component';

describe('CookeddishesComponent', () => {
  let component: CookeddishesComponent;
  let fixture: ComponentFixture<CookeddishesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CookeddishesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CookeddishesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
