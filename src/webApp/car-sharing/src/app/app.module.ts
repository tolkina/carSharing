import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";

import {AppComponent} from './app.component';
import {ProfileService} from "./service/profile.service";
import {AppRoutingModule} from "./app-routing/app-routing.module";
import {ProfileComponent} from "./profile/profile.component";
import {PassportDataService} from "./service/passport-data.service";
import {DriverLicenseService} from "./service/driver-license.service";

@NgModule({
  declarations: [
    AppComponent,
    ProfileComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule
  ],
  providers: [ProfileService, PassportDataService, DriverLicenseService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
