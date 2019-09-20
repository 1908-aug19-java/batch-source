import { NgModule } from '@angular/core';
import { Routes, RouterModule, ChildrenOutletContexts } from '@angular/router';
import { FirstComponent } from './components/first/first.component';
import { PipeDemoComponent } from './components/pipe-demo/pipe-demo.component';
import { HttpDemoComponent } from './components/http-demo/http-demo.component';
import { DatabindingComponent } from './components/databinding/databinding.component';
import { DirectivesComponent } from './components/directives/directives.component';
import { ADirectivesComponent } from './components/a-directives/a-directives.component';
import { SDirectivesComponent } from './components/s-directives/s-directives.component';
import { PostDetailComponent } from './components/post-detail/post-detail.component';

const routes: Routes = [{
  path: 'first',
  component: FirstComponent
},{
  path: 'pipes',
  component: PipeDemoComponent
}, {
  path: 'posts',
  component: HttpDemoComponent
}, {
  path: 'posts/:id',
  component: PostDetailComponent
}, {
  path: 'databind',
  component: DatabindingComponent
},{
  path: 'directives',
  component: DirectivesComponent,
  children: [
    { 
      path: 'attribute',
      component: ADirectivesComponent
    }, {
      path: 'structural',
      component: SDirectivesComponent
    }
  ]
}, {
  path: '**',
  pathMatch: 'full',
  redirectTo: ''
}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
