import {Component} from "@angular/core";
import {ActivatedRoute} from "@angular/router";
import {Subscription} from "rxjs/Subscription";
import {SecurityModel} from "../../security/security-model";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})

export class ProfileComponent {
  profileId: number;
  private subscription: Subscription;

  constructor(private activateRoute: ActivatedRoute, private securityModel: SecurityModel) {
    this.subscription = activateRoute.params.subscribe(params => this.profileId = params['profileId']);
  }

  ngOnInit() {
    this.profileId = this.securityModel.principal.id;
  }
}
