import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HighlightComponent } from './highlight/highlight.component';
import { ProfileComponent } from './profile/profile.component';
import { SelectComponent } from './select/select.component';
import { TableComponent } from './table/table.component';
import { UserComponent } from './user/user.component';
import { NavComponent } from './nav/nav.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule }    from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router'

const appRoutes: Routes = [

  {
    path: 'highlight', component: HighlightComponent,
  },

  {
    path: 'profile', component: ProfileComponent,
  },

  {
    path: 'select', component: SelectComponent,
  },

  {
    path: 'table', component: TableComponent,
  },

  {
    path: 'user', component: UserComponent
  },

  {
    path: '', redirectTo: 'highlight', pathMatch: 'full'
  }

]

@NgModule({
  declarations: [
    AppComponent,
    HighlightComponent,
    ProfileComponent,
    SelectComponent,
    TableComponent,
    UserComponent,
    NavComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule,
    RouterModule.forRoot(
      
      appRoutes,

      { 
        enableTracing: true
      }
    )
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
