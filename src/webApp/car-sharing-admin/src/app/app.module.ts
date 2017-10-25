import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";

import {AppComponent} from './app.component';
import {AppRoutingModule} from "./app-routing/app-routing.module";
import {HomePageAdminComponent} from "./home-page-admin/home-page-admin.component";
import {SetupAdminComponent} from './setup-admin/setup-admin.component';
import {TechnicalParameterService} from "./service/technical-parameter.service";
import {BrandComponent} from './technical-parameters/brand/brand.component';
import {ModelComponent} from './technical-parameters/model/model.component';
import {ColorComponent} from './technical-parameters/color/color.component';
import {DriveUnitComponent} from './technical-parameters/drive-unit/drive-unit.component';
import {FuelTypeComponent} from './technical-parameters/fuel-type/fuel-type.component';
import {InteriorMaterialComponent} from './technical-parameters/interior-material/interior-material.component';
import {TiresSeasonComponent} from './technical-parameters/tires-season/tires-season.component';
import {BodyTypeComponent} from './technical-parameters/body-type/body-type.component';
import {GearboxComponent} from "./technical-parameters/gearbox/gearbox.component";

@NgModule({
  declarations: [
    AppComponent,
    HomePageAdminComponent,
    SetupAdminComponent,
    GearboxComponent,
    BrandComponent,
    ModelComponent,
    ColorComponent,
    DriveUnitComponent,
    FuelTypeComponent,
    InteriorMaterialComponent,
    TiresSeasonComponent,
    BodyTypeComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule
  ],
  providers: [TechnicalParameterService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
