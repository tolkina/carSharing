export class ConfirmProfile {
  id: number;
  email: String;
  birthday: Date;
  drivingExperience: number;
  avatarUrl: String;
  confirmProfile: String;
  passportDataResponse: {
    id: number;
    firstName: String;
    lastName: String;
    middleName: String;
    series: String;
    number: number;
    personalNumber: String;
    placeOfIssue: String;
    dateOfIssue: Date;
    validUntil: Date;
    registrationPhotoUrl: String;
    photoUrl: String;
  };
  driverLicenseResponse: {
    id: number;
    seriesAndNumber: String;
    category: String;
  };
}
