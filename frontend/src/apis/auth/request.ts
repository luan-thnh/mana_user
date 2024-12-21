import api from '../axiosClient';
import { GetUserResponse } from '../user';
import { LoginRequest, LoginResponse, RegisterRequest, RegisterResponse } from './type';

export const login = async ({ username, password }: LoginRequest): Promise<LoginResponse> => {
  try {
    const response = await api.post('/auth/login', { username, password });
    return response.data;
  } catch (error) {
    console.error(error);
    throw error;
  }
};

export const register = async (data: RegisterRequest): Promise<RegisterResponse> => {
  try {
    const response = await api.post('/auth/register', data);
    return response.data;
  } catch (error) {
    console.error(error);
    throw error;
  }
};

export const me = async (token: string): Promise<GetUserResponse> => {
  try {
    const response = await api.get('/auth/me', {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    return response.data;
  } catch (error) {
    console.error(error);
    throw error;
  }
};
