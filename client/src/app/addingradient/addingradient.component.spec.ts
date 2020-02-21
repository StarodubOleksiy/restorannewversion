import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddingradientComponent } from './addingradient.component';

describe('AddingradientComponent', () => {
  let component: AddingradientComponent;
  let fixture: ComponentFixture<AddingradientComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddingradientComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddingradientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
