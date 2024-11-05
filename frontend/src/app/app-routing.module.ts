import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { 
    path: 'auth', 
    loadChildren: () => import('./auth/auth.module').then(m => m.AuthModule) 
  },
  { 
    path: 'author/:username', 
    loadChildren: () => import('./author/author.module').then(m => m.AuthorModule) 
  },
  { 
    path: 'editor/:username', 
    loadChildren: () => import('./editor/editor.module').then(m => m.EditorModule) 
  },
  { 
    path: 'evaluator/:username', 
    loadChildren: () => import('./evaluator/evaluator.module').then(m => m.EvaluatorModule) 
  },
  { path: '', redirectTo: 'auth', pathMatch: 'full' },
  { path: '**', redirectTo: 'auth' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }