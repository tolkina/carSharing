import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";

import {AppComponent} from './app.component';
import {ProfilesComponent} from './profiles/profiles.component';
import {ProfileService} from "./profile.service";
import {AppRoutingModule} from "./app-routing/app-routing.module";
import {HomePageAdminComponent} from "./home-page-admin/home-page-admin.component";
import {SetupAdminComponent} from './setup-admin/setup-admin.component';
import {MatTabsModule} from '@angular/material';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { GearboxComponent } from './gearbox/gearbox.component';
import {TechnicalParameterService} from "./technical-parameter.service";

@NgModule({
  declarations: [
    AppComponent,
    ProfilesComponent,
    HomePageAdminComponent,
    SetupAdminComponent,
    GearboxComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule,
    MatTabsModule,
    BrowserAnimationsModule
  ],
  providers: [ProfileService, TechnicalParameterService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
