import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DriveUnitComponent } from './drive-unit.component';

describe('DriveUnitComponent', () => {
  let component: DriveUnitComponent;
  let fixture: ComponentFixture<DriveUnitComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DriveUnitComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DriveUnitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
