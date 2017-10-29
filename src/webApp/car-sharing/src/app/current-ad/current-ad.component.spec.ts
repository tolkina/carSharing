import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CurrentAdComponent } from './current-ad.component';

describe('CurrentAdComponent', () => {
  let component: CurrentAdComponent;
  let fixture: ComponentFixture<CurrentAdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CurrentAdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CurrentAdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
