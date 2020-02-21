import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditdishesComponent } from './editdishes.component';

describe('EditdishesComponent', () => {
  let component: EditdishesComponent;
  let fixture: ComponentFixture<EditdishesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditdishesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditdishesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
