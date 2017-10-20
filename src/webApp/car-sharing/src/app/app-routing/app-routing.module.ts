import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";

import {SetupAdminComponent} from "../setup-admin/setup-admin.component";
import {HomePageAdminComponent} from "../home-page-admin/home-page-admin.component";

const routes: Routes = [
  {path: '', component: HomePageAdminComponent},
  {path: 'setup', component: SetupAdminComponent},
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
