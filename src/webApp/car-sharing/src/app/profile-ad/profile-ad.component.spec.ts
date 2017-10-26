import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileAdComponent } from './profile-ad.component';

describe('ProfileAdComponent', () => {
  let component: ProfileAdComponent;
  let fixture: ComponentFixture<ProfileAdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProfileAdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfileAdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
