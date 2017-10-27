import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GeneralParametersComponent } from './general-parameters.component';

describe('GeneralParametersComponent', () => {
  let component: GeneralParametersComponent;
  let fixture: ComponentFixture<GeneralParametersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GeneralParametersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GeneralParametersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
