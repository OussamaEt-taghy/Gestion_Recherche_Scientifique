import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AuthModule } from './auth/auth.module';
import { Demo_Ng_Material } from 'src/assets/Demo_Ng_Material';
import { ReactiveFormsModule } from '@angular/forms';
import { EditorModule } from './editor/editor.module';
import { EvaluatorModule } from './evaluator/evaluator.module';
import { AuthorModule } from './author/author.module';
import { HttpClientModule } from '@angular/common/http';
import { NgxUiLoaderModule } from 'ngx-ui-loader';
import { NgxSpinnerModule } from 'ngx-spinner';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatMenuModule } from '@angular/material/menu';
import { MatDialogModule } from '@angular/material/dialog';
import { MatSelectModule } from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';

import { MatPaginatorModule } from '@angular/material/paginator';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatDialogModule,
    BrowserAnimationsModule,
    AuthModule,
    AuthorModule,
    EditorModule,
    MatSelectModule,
    MatFormFieldModule,
    EvaluatorModule,
    Demo_Ng_Material,
    ReactiveFormsModule,
    HttpClientModule,
    NgxUiLoaderModule,
    NgxSpinnerModule,
    MatSidenavModule,
    MatMenuModule,
    MatDialogModule,
    MatPaginatorModule,

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
