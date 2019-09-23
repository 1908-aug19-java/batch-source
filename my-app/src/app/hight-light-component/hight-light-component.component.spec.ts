import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HightLightComponentComponent } from './hight-light-component.component';

describe('HightLightComponentComponent', () => {
  let component: HightLightComponentComponent;
  let fixture: ComponentFixture<HightLightComponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HightLightComponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HightLightComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
