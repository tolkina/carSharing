import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TiresSeasonComponent } from './tires-season.component';

describe('TiresSeasonComponent', () => {
  let component: TiresSeasonComponent;
  let fixture: ComponentFixture<TiresSeasonComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TiresSeasonComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TiresSeasonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
