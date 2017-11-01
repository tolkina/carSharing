import {Component} from '@angular/core';
import {Subscription} from "rxjs/Subscription";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})

export class ProfileComponent {

  profileId: number;

  private subscription: Subscription;

  constructor(private activateRoute: ActivatedRoute){
    this.subscription = activateRoute.params.subscribe(params => this.profileId = params['profileId']);
  }

  ngOnInit(){
    this.profileId = 1;
  }
}
