import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ContactListComponent } from './contact-list/contact-list.component';
import { ContactCreateComponent } from './contact-create/contact-create.component';
import { HomeComponent } from './home/home.component';
import { DataBindingComponent } from './Components/databinding/databinding.component';
import { NewComponentComponent } from './Components/new-component/new-component.component';
import { HightLightComponentComponent } from './hight-light-component/hight-light-component.component';
import { ProfileComponentComponent } from './profile-component/profile-component.component';
import { SelectComponentComponent } from './select-component/select-component.component';
import { TableComponentComponent } from './table-component/table-component.component';
import { UserComponentComponent } from './user-component/user-component.component';

const routes: Routes = [
  {path:  "", pathMatch:  "full",redirectTo:  "home"},
  {path: "home", component: HomeComponent},
  {path: "contact-create", component: ContactCreateComponent},
  {path: "contact-list", component: ContactListComponent},
  {path: "databinding", component: DataBindingComponent},  
  {path: "newComponent", component: NewComponentComponent},
  {path: "higlightComponent", component: HightLightComponentComponent},
  {path: "profileComponent", component: ProfileComponentComponent},
  {path: "selectComponent", component: SelectComponentComponent},
  {path: "tableComponent", component: TableComponentComponent},
  {path: "userComponent", component: UserComponentComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
