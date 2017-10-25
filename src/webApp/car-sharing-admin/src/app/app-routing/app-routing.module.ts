import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";

import {SetupAdminComponent} from "../setup-admin/setup-admin.component";
import {HomePageAdminComponent} from "../home-page-admin/home-page-admin.component";
import {BrandComponent} from "../technical-parameters/brand/brand.component";
import {InteriorMaterialComponent} from "../technical-parameters/interior-material/interior-material.component";
import {TiresSeasonComponent} from "../technical-parameters/tires-season/tires-season.component";
import {DriveUnitComponent} from "../technical-parameters/drive-unit/drive-unit.component";
import {FuelTypeComponent} from "../technical-parameters/fuel-type/fuel-type.component";
import {BodyTypeComponent} from "../technical-parameters/body-type/body-type.component";
import {GearboxComponent} from "../technical-parameters/gearbox/gearbox.component";
import {ModelComponent} from "../technical-parameters/model/model.component";
import {ColorComponent} from "../technical-parameters/color/color.component";

const routes: Routes = [
  {path: '', component: HomePageAdminComponent},
  {path: 'setup', component: SetupAdminComponent},
/*  {path: 'setup/brand', component: BrandComponent},
  {path: 'setup/domain', component: ModelComponent},
  {path: 'setup/gearbox', component: GearboxComponent},
  {path: 'setup/body-type', component: BodyTypeComponent},
  {path: 'setup/fuel-type', component: FuelTypeComponent},
  {path: 'setup/drive-unit', component: DriveUnitComponent},
  {path: 'setup/tires-season', component: TiresSeasonComponent},
  {path: 'setup/interior-material', component: InteriorMaterialComponent},
  {path: 'setup/color', component: ColorComponent}*/
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ],
  declarations: []
})
export class AppRoutingModule {
}
