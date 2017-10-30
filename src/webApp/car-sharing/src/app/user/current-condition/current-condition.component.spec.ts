import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CurrentConditionComponent } from './current-condition.component';

describe('CurrentConditionComponent', () => {
  let component: CurrentConditionComponent;
  let fixture: ComponentFixture<CurrentConditionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CurrentConditionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CurrentConditionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
