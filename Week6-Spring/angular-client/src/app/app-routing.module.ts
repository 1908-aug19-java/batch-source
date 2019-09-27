import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NewBookComponent } from './components/new-book/new-book.component';
import { LibraryComponent } from './components/library/library.component';

const routes: Routes = [{
  component: LibraryComponent,
  path: "library"
},{
  component: NewBookComponent,
  path: "new"
}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
