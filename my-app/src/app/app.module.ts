import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NewComponentComponent } from './Components/new-component/new-component.component';
import { DataBindingComponent } from './Components/databinding/databinding.component';
import { AboutComponent } from './about/about.component';
import { ContactComponent } from './contact/contact.component';
import { HomeComponent } from './home/home.component';
import { ContactCreateComponent } from './contact-create/contact-create.component';
import { ContactListComponent } from './contact-list/contact-list.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { HightLightComponentComponent } from './hight-light-component/hight-light-component.component';
import { ProfileComponentComponent } from './profile-component/profile-component.component';
import { SelectComponentComponent } from './select-component/select-component.component';
import { UserComponentComponent } from './user-component/user-component.component';
import { TableComponentComponent } from './table-component/table-component.component';


@NgModule({
  declarations: [
    AppComponent,
    NewComponentComponent,
    DataBindingComponent,
    
    AboutComponent,
    ContactComponent,
    HomeComponent,
    ContactCreateComponent,
    ContactListComponent,
    HeaderComponent,
    FooterComponent,
    HightLightComponentComponent,
    ProfileComponentComponent,
    SelectComponentComponent,
    UserComponentComponent,
    TableComponentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule 
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
