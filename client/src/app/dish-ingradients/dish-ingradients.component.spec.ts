import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DishIngradientsComponent } from './dish-ingradients.component';

describe('DishIngradientsComponent', () => {
  let component: DishIngradientsComponent;
  let fixture: ComponentFixture<DishIngradientsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DishIngradientsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DishIngradientsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
