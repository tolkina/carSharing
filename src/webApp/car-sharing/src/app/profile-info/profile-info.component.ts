// import 'rxjs/add/operator/switchMap'
// import {Component, OnInit} from '@angular/core';
// import {Profile} from "../profile";
// import {ProfileService} from "../profile.service";
// import {ActivatedRoute, Params} from "@angular/router";
//
// @Component({
//   selector: 'app-profile-info',
//   templateUrl: './profile-info.component.html',
//   styleUrls: ['./profile-info.component.css']
// })
// export class ProfileInfoComponent implements OnInit {
//
//   profile: Profile;
//
//   constructor(private profileService: ProfileService,
//               private route: ActivatedRoute) {
//   }
//
//   ngOnInit(): void {
//     this.route.params.switchMap((params: Params) => this.profileService.getProfile(+params['id']))
//       .subscribe(profile => this.profile = profile);
//   }
//
// }
