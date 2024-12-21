export interface LoginResponse {
  code: number;
  result: {
    token: string;
    authentication: boolean;
  };
  timestamp: string;
}

export interface LoginRequest {
  username: string;
  password: string;
}

export interface RegisterRequest {
  firstName: string;
  lastName: string;
  birthday: string;
  companyId?: string;
  username: string;
  password: string;
  confirmPassword: string;
}

export interface RegisterResponse {
  code: number;
  result: {
    id: string;
    username: string;
    firstName: string;
    lastName: string;
    birthday: string;
    companyId: string | null;
  };
  timestamp: string;
}
