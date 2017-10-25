import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InteriorMaterialComponent } from './interior-material.component';

describe('InteriorMaterialComponent', () => {
  let component: InteriorMaterialComponent;
  let fixture: ComponentFixture<InteriorMaterialComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InteriorMaterialComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InteriorMaterialComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
