import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthorComponent } from './author/author.component';

import { ConsultationArticleComponent } from './author/pages/consultation-article/consultation-article.component';
import { ProfileComponent } from './author/pages/profile/profile.component';
import { HomeComponent } from './author/pages/home/home.component';
import { MyGroupsComponent } from './author/pages/my-groups/my-groups.component';
import { MyEvaluationsComponent } from './author/pages/my-evaluations/my-evaluations.component';

const routes: Routes = [
  {
    path: '',
    component: AuthorComponent,
    children: [
      { path: '', redirectTo: 'home', pathMatch: 'full' },
      { path: 'home', component: HomeComponent },
      { path: 'consultation-article', component: ConsultationArticleComponent },
      { path: 'profile', component: ProfileComponent },
      {path: 'my-groups',component:MyGroupsComponent},
      {path: 'my-evaluations',component:MyEvaluationsComponent}
    ]
  },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthorRoutingModule { }