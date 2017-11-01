import {Component, OnInit} from '@angular/core';
import {ProfileService} from "../user/service/profile.service";
import {Router} from '@angular/router';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  errorMessage = "";
  user: any = {};

  constructor(private profileService: ProfileService, private router: Router) {
  }

  ngOnInit() {
  }

  onSubmit() {
    this.profileService.createProfile(this.user).then(res => this.router.navigateByUrl(''))
      .catch(err => this.errorMessage = err)
  }
}
