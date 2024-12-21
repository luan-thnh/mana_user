export interface User {
  id: string;
  username: string;
  firstName: string;
  lastName: string;
  birthday: string;
  companyId: string | null;
}

export interface GetUsersResponse {
  code: number;
  result: User[];
  timestamp: string;
}

export interface GetUserResponse {
  code: number;
  result: User;
  timestamp: string;
}
