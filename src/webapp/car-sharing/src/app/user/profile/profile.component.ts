import {Component} from '@angular/core';
import {ActivatedRoute} from '@angular/router'
import {Subscription} from 'rxjs/Subscription';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})

export class ProfileComponent {
  /*profileId: number;
  private subscription: Subscription;

  constructor(private activateRoute: ActivatedRoute) {
    this.subscription = activateRoute.params.subscribe(params => this.profileId = params['id']);
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }*/
}