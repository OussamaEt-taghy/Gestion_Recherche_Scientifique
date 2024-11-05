import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthRoutingModule } from './auth-routing.module';
import { LoginComponent } from './login/login.component';

import { RecoverPassWordComponent } from './recover-pass-word/recover-pass-word.component';
import { Demo_Ng_Material } from 'src/assets/Demo_Ng_Material';

import { RegisterComponent } from './register/register.component';
import { MatDialogModule } from '@angular/material/dialog';
import { ReactiveFormsModule } from '@angular/forms';
import { MatSidenavContainer, MatSidenavModule } from '@angular/material/sidenav';


@NgModule({
  declarations: [
    LoginComponent,
    RecoverPassWordComponent,
    RegisterComponent
  ],
  imports: [
    CommonModule,
    AuthRoutingModule,
    Demo_Ng_Material,
    ReactiveFormsModule,
    MatDialogModule,
    MatSidenavModule
  ]
})
export class AuthModule { }
