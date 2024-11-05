import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecoverPassWordComponent } from './recover-pass-word.component';

describe('RecoverPassWordComponent', () => {
  let component: RecoverPassWordComponent;
  let fixture: ComponentFixture<RecoverPassWordComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecoverPassWordComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RecoverPassWordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
