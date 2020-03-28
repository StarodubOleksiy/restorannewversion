import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RestoranSchemaComponent } from './restoran-schema.component';

describe('RestoranSchemaComponent', () => {
  let component: RestoranSchemaComponent;
  let fixture: ComponentFixture<RestoranSchemaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RestoranSchemaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RestoranSchemaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
