import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";

import {AppComponent} from './app.component';
import {ProfilesComponent} from './profiles/profiles.component';
import {ProfileService} from "./profile.service";
import {AppRoutingModule} from "./app-routing/app-routing.module";
import {ProfileComponent} from "./profile/profile.component";

@NgModule({
  declarations: [
    AppComponent,
    ProfilesComponent,
    ProfileComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule,
  ],
  providers: [ProfileService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
