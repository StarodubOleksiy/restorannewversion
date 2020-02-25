import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditDishIngradientComponent } from './edit-dish-ingradient.component';

describe('EditDishIngradientComponent', () => {
  let component: EditDishIngradientComponent;
  let fixture: ComponentFixture<EditDishIngradientComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditDishIngradientComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditDishIngradientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
