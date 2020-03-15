import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddcookeddishComponent } from './addcookeddish.component';

describe('AddcookeddishComponent', () => {
  let component: AddcookeddishComponent;
  let fixture: ComponentFixture<AddcookeddishComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddcookeddishComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddcookeddishComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
