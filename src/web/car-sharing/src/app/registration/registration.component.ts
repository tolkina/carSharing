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
  // emailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

  constructor(private profileService: ProfileService, private router: Router) {
  }

  ngOnInit() {
  }

  onSubmit() {
    this.profileService.createProfile(this.user)
      .then(res => this.router.navigateByUrl('login'))
      .catch(err => this.errorMessage = err)
  }
}
