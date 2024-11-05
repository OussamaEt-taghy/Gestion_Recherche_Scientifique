import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthorRoutingModule } from './author-routing.module';
import { AuthorComponent } from './author/author.component';
import { Demo_Ng_Material } from 'src/assets/Demo_Ng_Material';

import { ConsultationArticleComponent } from './author/pages/consultation-article/consultation-article.component';
import { ProfileComponent } from './author/pages/profile/profile.component';
import { MatSidenavModule } from '@angular/material/sidenav';
import { HomeComponent } from './author/pages/home/home.component';
import { MyGroupsComponent } from './author/pages/my-groups/my-groups.component';
import { MatMenuModule } from '@angular/material/menu';
import { MyEvaluationsComponent } from './author/pages/my-evaluations/my-evaluations.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';


@NgModule({
  declarations: [
    AuthorComponent,
   
    ConsultationArticleComponent,
    ProfileComponent,
    HomeComponent,
    MyGroupsComponent,
    MyEvaluationsComponent,
 
    
  ],
  imports: [
    CommonModule,
    AuthorRoutingModule,
    Demo_Ng_Material,
    MatSidenavModule,
    MatMenuModule,
    ReactiveFormsModule,
    MatDialogModule,
    
   
  ]
})
export class AuthorModule { }
