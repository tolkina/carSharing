import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TechnicalParametersComponent } from './technical-parameters.component';

describe('TechnicalParametersComponent', () => {
  let component: TechnicalParametersComponent;
  let fixture: ComponentFixture<TechnicalParametersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TechnicalParametersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TechnicalParametersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
