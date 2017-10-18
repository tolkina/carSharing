import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";

import {ProfilesComponent} from "../profiles/profiles.component";
import {SetupAdminComponent} from "../setup-admin/setup-admin.component";
import {HomePageAdminComponent} from "../home-page-admin/home-page-admin.component";

const routes: Routes = [
  {path: 'profiles', component: ProfilesComponent},
  {path: '', component: HomePageAdminComponent},
  {path: 'setup', component: SetupAdminComponent}
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
