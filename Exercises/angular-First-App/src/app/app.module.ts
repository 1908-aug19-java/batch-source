import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavComponent } from './components/nav/nav.component';
import { HighlightComponent } from './components/highlight/highlight.component';
import { ProfileComponent } from './components/profile/profile.component';
import { SelectComponent } from './components/select/select.component';
import { TableComponent } from './components/table/table.component';
import { UserComponent } from './components/user/user.component';
import { UserService } from './services/user.service';



@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    HighlightComponent,
    ProfileComponent,
    SelectComponent,
    TableComponent,
    UserComponent
   
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule

  ],
  providers: [
    UserService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
