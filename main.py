#!usr/bin/python3
import argparse

def parse_arguments() -> argparse.Namespace:
    parser = argparse.ArgumentParser()
    parser.add_argument(
        "--service-account",
        dest="server_system_service_account_name",
        required=True,
        type=str,
        help="The Service Account user name from another system it is going to interact."
    )
    parser.add_argument(
        "--auth-token",
        dest="server_system_api_auth_token",
        required=True,
        type=str,
        help="The auth token for accessing server_system APIs."
    )
    parser.add_argument(
        "--snow-endpoint",
        dest="server_system_base_url",
        required=True,
        type=str,
        help="The base URL for server_system endpoint."
    )
    parser.add_argument(
        "--project-name",
        dest="project_name",
        required=True,
        type=str,
        choices=["Project-Igloo", "Project-Titan"],
        help="Name of the project for deployment"
    )
    return parser.parse_args()

  if __name__ == '__main__':
    try:
      passed_arguments = parse_arguments()
      print (passed_arguments.server_system_service_account_name)
    except Exception as e:
      logger.error(e)
      sys.exit()
