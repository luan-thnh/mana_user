'use client';

import { login } from '@/apis/auth';
import { Button, Input } from '@nextui-org/react';
import { useState } from 'react';
import Cookies from 'js-cookie'; // Import js-cookie to handle cookies
import { useRouter } from 'next/navigation';
import Link from 'next/link';
import Image from 'next/image';
import toast from 'react-hot-toast';

const Login = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState<string | null>(null);

  const router = useRouter();

  const handleSubmit = async () => {
    try {
      const data = await login({ username, password });

      if (data?.result?.token) {
        Cookies.set('auth_token', data.result.token, { expires: 7, path: '/' });
        toast.success('Login successful!');
        router.push('/');
      }
    } catch (err: any) {
      setError('Login failed');
      toast.error('Login failed. Please try again.');
    }
  };

  return (
    <div className="flex h-screen">
      <div className="w-1/2 flex justify-center items-center bg-blue-50">
        <div className="w-[550px] px-6">
          <h2 className="text-3xl font-bold text-blue-600 mb-10 text-center">Login</h2>

          {error && <small className="text-red-500 text-center block mb-4">{error}</small>}

          <Input
            fullWidth
            label="Username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            className="mb-6"
            color="primary"
            aria-label="Username"
          />

          <Input
            fullWidth
            label="Password"
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            className="mb-6"
            color="primary"
            aria-label="Password"
          />

          <Button
            onPress={handleSubmit}
            color="primary"
            size="lg"
            fullWidth
            aria-label="Login"
            className="bg-blue-600 text-white hover:bg-blue-700 focus:outline-none"
          >
            Login
          </Button>

          <div className="mt-4 text-center">
            Don{"'"}t have an account?{' '}
            <Link href="/register" className="text-blue-600 hover:underline">
              Register here
            </Link>
          </div>
        </div>
      </div>

      <div className="w-1/2 bg-blue-500 flex justify-center items-center bg-[url('/images/image_form.png')] bg-cover bg-no-repeat bg-center"></div>
    </div>
  );
};

export default Login;
